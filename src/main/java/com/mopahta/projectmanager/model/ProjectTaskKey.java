package com.mopahta.projectmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter @NoArgsConstructor
public class ProjectTaskKey implements Serializable {

    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTaskKey that = (ProjectTaskKey) o;
        return id.equals(that.id) && projectId.equals(that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId);
    }
}
