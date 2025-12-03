package com.github.jianlu8023.example.integration.middleware.properties;

import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
@ConfigurationProperties(prefix = "push")
public class PushProperties {

    private StatusProperties status;


    public PushProperties() {
    }

    public PushProperties(StatusProperties status) {
        this.status = status;
    }


    public StatusProperties getStatus() {
        return status;
    }

    public void setStatus(StatusProperties status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PushProperties that = (PushProperties) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "PushProperties{" +
                "status=" + status +
                '}';
    }
}
