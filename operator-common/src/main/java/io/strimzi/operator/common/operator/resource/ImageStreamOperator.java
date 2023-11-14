/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.operator.common.operator.resource;

import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.openshift.api.model.ImageStream;
import io.fabric8.openshift.api.model.ImageStreamList;
import io.fabric8.openshift.client.OpenShiftClient;
import io.vertx.core.Vertx;

/**
 * Operations for {@code ImageStream}s.
 */
public class ImageStreamOperator extends AbstractNamespacedResourceOperator<OpenShiftClient, ImageStream, ImageStreamList, Resource<ImageStream>> {
    /**
     * Constructor
     * @param vertx The Vertx instance
     * @param client The OpenShift client
     */
    public ImageStreamOperator(Vertx vertx, OpenShiftClient client) {
        super(vertx, client, "ImageStream");
    }
    /**
     * Constructor
     * @param vertx The Vertx instance
     * @param client The OpenShift client
     * @param useServerSideApply Whether to use server side apply
     */
    public ImageStreamOperator(Vertx vertx, OpenShiftClient client, boolean useServerSideApply) {
        super(vertx, client, "ImageStream", useServerSideApply);
    }

    @Override
    protected MixedOperation<ImageStream, ImageStreamList, Resource<ImageStream>> operation() {
        return client.imageStreams();
    }
}
