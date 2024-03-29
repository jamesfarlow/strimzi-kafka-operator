/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.operator.cluster;

import io.strimzi.operator.common.config.FeatureGate;
import io.strimzi.operator.common.config.FeatureGatesParser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class for handling the configuration of feature gates
 */
public class FeatureGates {
    /* test */ static final FeatureGates NONE = new FeatureGates("");

    private static final String USE_KRAFT = "UseKRaft";
    private static final String UNIDIRECTIONAL_TOPIC_OPERATOR = "UnidirectionalTopicOperator";
    private static final String USE_SERVER_SIDE_APPLY = "UseServerSideApply";

    private final Map<String, FeatureGate> featureGates = Map.ofEntries(
            Map.entry(USE_KRAFT, new FeatureGate(USE_KRAFT, true)),
            Map.entry(UNIDIRECTIONAL_TOPIC_OPERATOR, new FeatureGate(UNIDIRECTIONAL_TOPIC_OPERATOR, true)),
            Map.entry(USE_SERVER_SIDE_APPLY, new FeatureGate(USE_SERVER_SIDE_APPLY, false))
    );

    /**
     * Constructs the feature gates configuration.
     *
     * @param featureGateConfig String with comma separated list of enabled or disabled feature gates
     */
    public FeatureGates(String featureGateConfig) {
        new FeatureGatesParser(featureGateConfig).applyFor(featureGates);
    }

    /**
     * @return  Returns true when the UseKRaft feature gate is enabled
     */
    public boolean useKRaftEnabled() {
        return featureGates.get(USE_KRAFT).isEnabled();
    }

    /**
     * @return  Returns true when the UnidirectionalTopicOperator feature gate is enabled
     */
    public boolean unidirectionalTopicOperatorEnabled() {
        return featureGates.get(UNIDIRECTIONAL_TOPIC_OPERATOR).isEnabled();
    }

    /**
     * @return  Returns true when the UseServerSideApply feature gate is enabled
     */
    public boolean useServerSideApply() {
        return featureGates.get(USE_SERVER_SIDE_APPLY).isEnabled();
    }

    /**
     * Returns a list of all Feature gates. Used for testing.
     *
     * @return  List of all Feature Gates
     */
    /*test*/ List<FeatureGate> allFeatureGates()  {
        return featureGates.values().stream().toList();
    }

    @Override
    public String toString() {
        String featureGatesValues = featureGates.entrySet()
                .stream()
                .map(featureGate -> featureGate.getKey() + "=" + featureGate.getValue().isEnabled())
                .collect(Collectors.joining(","));
        return "FeatureGates(%s)".formatted(featureGatesValues);
    }
}
