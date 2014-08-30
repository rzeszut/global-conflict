package edu.globalconflict.serializer;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import edu.globalconflict.entity.Component;
import edu.globalconflict.entity.EntityManager;
import edu.globalconflict.entity.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author mateusz
 * @since 30.08.14
 */
public class EntityManagerSerializer implements Json.Serializer<EntityManager> {
    @Override
    public void write(Json json, EntityManager object, Class knownType) {
        json.writeObjectStart();

        writeComponentMap(json, object);
        writeTags(json, object);

        json.writeObjectEnd();
    }

    private void writeTags(Json json, EntityManager object) {
        json.writeArrayStart("tagsToEntities");

        for (Map.Entry<Tag, UUID> entry : object.tagsToEntities.entrySet()) {
            json.writeObjectStart();

            final Tag tag = entry.getKey();
            json.writeValue("namespace", tag.namespace);
            json.writeValue("tag", tag.tag);
            json.writeValue("entity", entry.getValue().toString());

            json.writeObjectEnd();
        }

        json.writeArrayEnd();
    }

    private void writeComponentMap(Json json, EntityManager object) {
        json.writeObjectStart("componentMap");

        for (Map.Entry<Class<? extends Component>, Map<UUID, ? extends Component>> entry
                : object.componentMap.entrySet()) {
            // write class name
            final Class<? extends Component> componentClass = entry.getKey();
            json.writeObjectStart(componentClass.getName());

            // serialize components
            final Map<UUID, ? extends Component> components = entry.getValue();
            for (Map.Entry<UUID, ? extends Component> componentEntry : components.entrySet()) {
                json.writeValue(componentEntry.getKey().toString(),
                        componentEntry.getValue(), componentClass);
            }

            json.writeObjectEnd();
        }

        json.writeObjectEnd();
    }

    @Override
    public EntityManager read(Json json, JsonValue jsonData, Class type) {
        try {
            final Map<Class<? extends Component>, Map<UUID, ? extends Component>> componentMap =
                    readComponentMap(json, jsonData);
            final Map<Tag, UUID> tagsToEntities = readTags(jsonData);

            final EntityManager entityManager = new EntityManager();
            entityManager.componentMap = componentMap;
            entityManager.tagsToEntities = tagsToEntities;
            return entityManager;
        } catch (ClassNotFoundException e) {
            // should never happen
            throw new RuntimeException(e);
        }
    }

    private Map<Tag, UUID> readTags(JsonValue jsonData) {
        final Map<Tag, UUID> tagsToEntities = new HashMap<>();

        for (JsonValue entry : jsonData.get("tagsToEntities")) {
            final Tag.Namespace namespace = Tag.Namespace.valueOf(entry.getString("namespace"));
            final String tag = entry.getString("tag");
            final UUID entity = UUID.fromString(entry.getString("entity"));

            tagsToEntities.put(new Tag(namespace, tag), entity);
        }

        return tagsToEntities;
    }

    @SuppressWarnings("unchecked")
    private Map<Class<? extends Component>, Map<UUID, ? extends Component>> readComponentMap(Json json, JsonValue jsonData)
            throws ClassNotFoundException {
        final Map<Class<? extends Component>, Map<UUID, ? extends Component>> componentMap = new HashMap<>();

        for (JsonValue entryJSON : jsonData.get("componentMap")) {
            // read class
            final Class<? extends Component> componentClass =
                    (Class<? extends Component>) Class.forName(entryJSON.name());

            // read map (entity -> component)
            final Map<UUID, Component> entityComponentMap = new HashMap<>();
            for (JsonValue componentEntryJSON : entryJSON) {
                final String uuid = componentEntryJSON.name();
                final Component component = json.readValue(componentClass, componentEntryJSON);

                entityComponentMap.put(UUID.fromString(uuid), component);
            }

            componentMap.put(componentClass, entityComponentMap);
        }

        return componentMap;
    }
}
