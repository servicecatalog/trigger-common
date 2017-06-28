/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2017                                           
 *                                                                                                                                 
 *  Creation Date: Jun 6, 2017                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.common.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.oscm.common.interfaces.data.Event;
import org.oscm.common.interfaces.events.EventSource;
import org.oscm.common.interfaces.keys.EntityKey;
import org.oscm.common.util.ConfigurationManager;

/**
 * Stream class for event tables with kafka. Takes events from a topic and saves
 * them in an internal state store.
 * 
 * @author miethaner
 */
public class EventTable<E extends Event> extends Stream
        implements EventSource<E> {

    public static final String STATE_STORE = "event_store";

    private EntityKey entity;

    private KafkaStreams localStreams;
    private ReadOnlyKeyValueStore<UUID, E> store;

    /**
     * Creates a kafka table for the given entity.
     * 
     * @param application
     *            the application key
     * @param clazz
     *            the event class
     */
    public EventTable(EntityKey entity) {
        this.entity = entity;
    }

    @Override
    protected KafkaStreams initStreams() {

        KStreamBuilder builder = new KStreamBuilder();
        builder.table(new UUIDSerializer(),
                new DataSerializer<>(entity.getEntityClass()),
                buildEventTopic(entity), STATE_STORE);

        localStreams = new KafkaStreams(builder,
                new StreamsConfig(getConfig()));
        return localStreams;
    }

    private Map<String, Object> getConfig() {

        Map<String, Object> config = new HashMap<>();
        ConfigurationManager.getInstance()
                .getProprietaryConfig(KafkaConfig.values())
                .forEach((key, value) -> config.put(key, value));

        config.put(StreamsConfig.APPLICATION_ID_CONFIG,
                buildApplicationId(entity.getEntityName()));

        return config;
    }

    @Override
    public E get(UUID id) {
        if (store == null) {
            store = localStreams.store(STATE_STORE,
                    QueryableStoreTypes.keyValueStore());
        }

        return store.get(id);
    }

    @Override
    public List<E> getAll() {
        if (store == null) {
            store = localStreams.store(STATE_STORE,
                    QueryableStoreTypes.keyValueStore());
        }

        List<E> list = new ArrayList<>();
        store.all().forEachRemaining((record) -> list.add(record.value));

        return list;
    }
}
