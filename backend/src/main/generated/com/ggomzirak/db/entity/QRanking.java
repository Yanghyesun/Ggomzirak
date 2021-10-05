package com.ggomzirak.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRanking is a Querydsl query type for Ranking
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRanking extends EntityPathBase<Ranking> {

    private static final long serialVersionUID = 281936879L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRanking ranking1 = new QRanking("ranking1");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final NumberPath<Integer> posts = createNumber("posts", Integer.class);

    public final NumberPath<Integer> ranking = createNumber("ranking", Integer.class);

    public final QUser user;

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public QRanking(String variable) {
        this(Ranking.class, forVariable(variable), INITS);
    }

    public QRanking(Path<? extends Ranking> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRanking(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRanking(PathMetadata metadata, PathInits inits) {
        this(Ranking.class, metadata, inits);
    }

    public QRanking(Class<? extends Ranking> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

