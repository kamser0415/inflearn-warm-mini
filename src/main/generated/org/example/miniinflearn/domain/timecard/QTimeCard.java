package org.example.miniinflearn.domain.timecard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTimeCard is a Querydsl query type for TimeCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeCard extends EntityPathBase<TimeCard> {

    private static final long serialVersionUID = 489508970L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTimeCard timeCard = new QTimeCard("timeCard");

    public final DateTimePath<java.time.LocalDateTime> attendLocalDateTime = createDateTime("attendLocalDateTime", java.time.LocalDateTime.class);

    public final org.example.miniinflearn.domain.employee.QEmployee employee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<WORKSTATIONS> WORKSTATIONS = createEnum("WORKSTATIONS", WORKSTATIONS.class);

    public QTimeCard(String variable) {
        this(TimeCard.class, forVariable(variable), INITS);
    }

    public QTimeCard(Path<? extends TimeCard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTimeCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTimeCard(PathMetadata metadata, PathInits inits) {
        this(TimeCard.class, metadata, inits);
    }

    public QTimeCard(Class<? extends TimeCard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new org.example.miniinflearn.domain.employee.QEmployee(forProperty("employee")) : null;
    }

}

