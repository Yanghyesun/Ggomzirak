package com.ggomzirak.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTips is a Querydsl query type for Tips
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTips extends EntityPathBase<Tips> {

    private static final long serialVersionUID = -302391777L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTips tips = new QTips("tips");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.sql.Timestamp> edit_time = createDateTime("edit_time", java.sql.Timestamp.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath image = createString("image");

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final StringPath tags = createString("tags");

    public final StringPath thumbnail = createString("thumbnail");

    public final DateTimePath<java.sql.Timestamp> time = createDateTime("time", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final QUser user;

    public QTips(String variable) {
        this(Tips.class, forVariable(variable), INITS);
    }

    public QTips(Path<? extends Tips> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTips(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTips(PathMetadata metadata, PathInits inits) {
        this(Tips.class, metadata, inits);
    }

    public QTips(Class<? extends Tips> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

