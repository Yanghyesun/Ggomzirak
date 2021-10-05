package com.ggomzirak.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComments is a Querydsl query type for Comments
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QComments extends EntityPathBase<Comments> {

    private static final long serialVersionUID = -708993701L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComments comments = new QComments("comments");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.sql.Timestamp> edit_time = createDateTime("edit_time", java.sql.Timestamp.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final QPostType postType;

    public final DateTimePath<java.sql.Timestamp> time = createDateTime("time", java.sql.Timestamp.class);

    public final QUser user;

    public QComments(String variable) {
        this(Comments.class, forVariable(variable), INITS);
    }

    public QComments(Path<? extends Comments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComments(PathMetadata metadata, PathInits inits) {
        this(Comments.class, metadata, inits);
    }

    public QComments(Class<? extends Comments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.postType = inits.isInitialized("postType") ? new QPostType(forProperty("postType")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

