package edu.globalconflict.entity;

import java.util.*;

/**
 * @author mateusz
 * @since 15.08.14
 */
public final class EntityManager {
    private Map<Class<? extends Component>, Map<UUID, ? extends Component>> componentMap;
    private Map<UUID, List<String>> entitiesToTags;
    private Map<String, List<UUID>> tagsToEntities;

    public EntityManager() {
        componentMap = new HashMap<>();
        entitiesToTags = new HashMap<>();
        tagsToEntities = new HashMap<>();
    }

    public UUID newEntity() {
        return UUID.randomUUID();
    }

    public UUID newTaggedEntity(String... tags) {
        final UUID entity = newEntity();
        for (String tag : tags) {
            tagEntity(entity, tag);
        }
        return entity;
    }

    public void tagEntity(UUID entity, String tag) {
        List<String> tags = entitiesToTags.get(entity);
        if (tags == null) {
            tags = new LinkedList<>();
            tags.add(tag);
            entitiesToTags.put(entity, tags);
        } else {
            tags.add(tag);
        }

        List<UUID> entities = tagsToEntities.get(tag);
        if (entities == null) {
            entities = new LinkedList<>();
            entities.add(entity);
            tagsToEntities.put(tag, entities);
        } else {
            entities.add(entity);
        }
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

    public List<UUID> getEntitiesForTag(String tag) {
        return tagsToEntities.get(tag);
    }

    public UUID getEntityForTag(String tag) {
        final List<UUID> entities = tagsToEntities.get(tag);
        return entities == null ? null : entities.get(0);
    }
}
