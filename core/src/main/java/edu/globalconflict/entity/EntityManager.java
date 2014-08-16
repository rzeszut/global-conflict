package edu.globalconflict.entity;

import java.util.*;

/**
 * @author mateusz
 * @since 15.08.14
 */
public final class EntityManager {
    private Map<Class<? extends Component>, Map<UUID, ? extends Component>> componentMap;
    private Map<UUID, Tag> entitiesToTags;
    private Map<Tag, UUID> tagsToEntities;

    private Tag tmpTag = new Tag();

    public EntityManager() {
        componentMap = new HashMap<>();
        entitiesToTags = new HashMap<>();
        tagsToEntities = new HashMap<>();
    }

    public UUID newEntity() {
        return UUID.randomUUID();
    }

    public UUID newTaggedEntity(Tag.Namespace namespace, String tag) {
        final UUID entity = newEntity();
        tagEntity(entity, namespace, tag);
        return entity;
    }

    public void tagEntity(UUID entity, Tag.Namespace namespace, String tagString) {
        Tag tag = entitiesToTags.get(entity);
        if (tag == null) {
            tag = new Tag(namespace, tagString);
            entitiesToTags.put(entity, tag);
        } else {
            tag.set(namespace, tagString);
        }

        tagsToEntities.put(tag, entity);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> void addComponent(UUID entity, T component) {
        Map<UUID, T> components = (Map<UUID, T>) componentMap.get(component.getClass());
        if (components == null) {
            components = new HashMap<>();
            components.put(entity, component);
            componentMap.put(component.getClass(), components);
        } else {
            components.put(entity, component);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(UUID entity, Class<T> type) {
        final Map<UUID, ? extends Component> components = componentMap.get(type);
        return components == null ? null : (T) components.get(entity);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> Collection<T> getComponentsForType(Class<T> type) {
        final Map<UUID, T> components = (Map<UUID, T>) componentMap.get(type);
        return components == null ? Collections.<T>emptyList() : components.values();
    }

    public Collection<UUID> getEntitiesForType(Class<? extends Component> type) {
        final Map<UUID, ? extends Component> components = componentMap.get(type);
        return components == null ? Collections.<UUID>emptyList() : components.keySet();
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> Set<Map.Entry<UUID, T>> getEntitiesWithComponentsForType(Class<T> type) {
        final Map<UUID, T> components = (Map<UUID, T>) componentMap.get(type);
        return components == null ?
                Collections.<Map.Entry<UUID, T>>emptySet() :
                components.entrySet();
    }

    public UUID getEntityForTag(Tag.Namespace namespace, String tag) {
        tmpTag.set(namespace, tag);
        return tagsToEntities.get(tmpTag);
    }
}
