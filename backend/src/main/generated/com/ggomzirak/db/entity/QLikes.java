package com.ggomzirak.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikes is a Querydsl query type for Likes
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLikes extends EntityPathBase<Likes> {

    private static final long serialVersionUID = -791603787L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikes likes = new QLikes("likes");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final QPostType postType;

    public final QUser user;

    public QLikes(String variable) {
        this(Likes.class, forVariable(variable), INITS);
    }

    public QLikes(Path<? extends Likes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikes(PathMetadata metadata, PathInits inits) {
        this(Likes.class, metadata, inits);
    }

    public QLikes(Class<? extends Likes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.postType = inits.isInitialized("postType") ? new QPostType(forProperty("postType")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

