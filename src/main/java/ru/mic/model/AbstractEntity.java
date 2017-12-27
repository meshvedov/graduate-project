package ru.mic.model;

import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    protected AbstractEntity() {
    }

    protected AbstractEntity(Integer id) {
        this.id = id;
    }

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), id);
    }

    // TODO: 27.12.17 add equals() and hashCode()

}
