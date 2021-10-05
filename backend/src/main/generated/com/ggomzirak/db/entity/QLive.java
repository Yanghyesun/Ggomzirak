package com.ggomzirak.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLive is a Querydsl query type for Live
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLive extends EntityPathBase<Live> {

    private static final long serialVersionUID = -302629933L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLive live = new QLive("live");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.time.LocalDateTime> endtime = createDateTime("endtime", java.time.LocalDateTime.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> maxviewers = createNumber("maxviewers", Integer.class);

    public final DateTimePath<java.sql.Timestamp> starttime = createDateTime("starttime", java.sql.Timestamp.class);

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final QUser user;

    public final StringPath video = createString("video");

    public final NumberPath<Integer> viewers = createNumber("viewers", Integer.class);

    public QLive(String variable) {
        this(Live.class, forVariable(variable), INITS);
    }

    public QLive(Path<? extends Live> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLive(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLive(PathMetadata metadata, PathInits inits) {
        this(Live.class, metadata, inits);
    }

    public QLive(Class<? extends Live> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

