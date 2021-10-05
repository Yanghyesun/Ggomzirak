package com.ggomzirak.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShorts is a Querydsl query type for Shorts
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QShorts extends EntityPathBase<Shorts> {

    private static final long serialVersionUID = 1429698718L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShorts shorts = new QShorts("shorts");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.sql.Timestamp> edit_time = createDateTime("edit_time", java.sql.Timestamp.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final StringPath tags = createString("tags");

    public final StringPath thumbnail = createString("thumbnail");

    public final DateTimePath<java.sql.Timestamp> time = createDateTime("time", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final QUser user;

    public final StringPath video = createString("video");

    public final NumberPath<Long> views = createNumber("views", Long.class);

    public QShorts(String variable) {
        this(Shorts.class, forVariable(variable), INITS);
    }

    public QShorts(Path<? extends Shorts> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShorts(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShorts(PathMetadata metadata, PathInits inits) {
        this(Shorts.class, metadata, inits);
    }

    public QShorts(Class<? extends Shorts> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

