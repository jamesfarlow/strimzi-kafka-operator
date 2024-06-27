/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.operator.cluster.operator.assembly;

import io.vertx.junit5.VertxExtension;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Tests in this class mirrors KafkaConnectAssemblyOperatorMockTest with +UserServerSideApply
 */
@ExtendWith(VertxExtension.class)
public class KafkaConnectAssemblyOperatorWithSSAMockTest extends KafkaConnectAssemblyOperatorMockTest {
    @Override
    protected boolean getSSA() {
        return true;
    }
}