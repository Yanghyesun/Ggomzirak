package com.ggomzirak.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQna is a Querydsl query type for Qna
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQna extends EntityPathBase<Qna> {

    private static final long serialVersionUID = 1237168669L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQna qna = new QQna("qna");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.sql.Timestamp> edit_time = createDateTime("edit_time", java.sql.Timestamp.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath image = createString("image");

    public final StringPath tags = createString("tags");

    public final DateTimePath<java.sql.Timestamp> time = createDateTime("time", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final QUser user;

    public QQna(String variable) {
        this(Qna.class, forVariable(variable), INITS);
    }

    public QQna(Path<? extends Qna> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQna(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQna(PathMetadata metadata, PathInits inits) {
        this(Qna.class, metadata, inits);
    }

    public QQna(Class<? extends Qna> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

