package com.avondock.core.common.http.contracts;

import com.avondock.core.common.http.WebClientAdapter;

public abstract class AbstractWebClient {

    private final WebClientAdapter client;
    private final String version;

    protected AbstractWebClient(AbstractWebClient.Builder builder) {
        builder.validate();
        client = builder.client;
        version = builder.version;
    }

    public String getVersion() {
        return version;
    }

    public WebClientAdapter getClient() {
        return client;
    }

    public abstract static class Builder {
        public String version;
        private WebClientAdapter client;

        public Builder() {

        }

        public AbstractWebClient.Builder client(WebClientAdapter client) {
            this.client = client;
            return this;
        }

        public AbstractWebClient.Builder version(String version) {
            this.version = version;
            return this;
        }

        protected void validate() {

        }
    }
}
