package edu.globalconflict.entity;

/**
 * @author mateusz
 * @since 16.08.14
 */
public final class Tag {
    public Namespace namespace;
    public String tag;

    Tag() {
    }

    public Tag(Namespace namespace, String tag) {
        this.namespace = namespace;
        this.tag = tag;
    }

    void set(Namespace namespace, String tag) {
        this.namespace = namespace;
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Tag tag1 = (Tag) o;
        return namespace == tag1.namespace && tag.equals(tag1.tag);
    }

    @Override
    public int hashCode() {
        int result = namespace.hashCode();
        result = 31 * result + tag.hashCode();
        return result;
    }

    public static enum Namespace {
        GAME,
        TERRITORY
    }
}
