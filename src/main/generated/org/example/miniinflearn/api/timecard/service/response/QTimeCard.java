package org.example.miniinflearn.api.timecard.service.response;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeCard is a Querydsl query type for TimeCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTimeCard extends EntityPathBase<TimeCard> {

    private static final long serialVersionUID = 352102970L;

    public static final QTimeCard timeCard = new QTimeCard("timeCard");

    public final DateTimePath<java.time.LocalDateTime> attendAt = createDateTime("attendAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> employeeId = createNumber("employeeId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> leaveAt = createDateTime("leaveAt", java.time.LocalDateTime.class);

    public QTimeCard(String variable) {
        super(TimeCard.class, forVariable(variable));
    }

    public QTimeCard(Path<? extends TimeCard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeCard(PathMetadata metadata) {
        super(TimeCard.class, metadata);
    }

}

