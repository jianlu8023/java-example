package com.github.jianlu8023.example.integration.middleware.properties;

import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
@ConfigurationProperties(prefix = "status")
public class StatusProperties {

    private Boolean enabled = false;

    private String url;

    public StatusProperties() {
    }

    public StatusProperties(Boolean enabled, String url) {
        this.enabled = enabled;
        this.url = url;
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusProperties that = (StatusProperties) o;
        return Objects.equals(enabled, that.enabled) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, url);
    }

    @Override
    public String toString() {
        return "StatusProperties{" +
                "enabled=" + enabled +
                ", url='" + url + '\'' +
                '}';
    }
}
