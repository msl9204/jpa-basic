package jpabasic.dialect;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyMysqlDialect extends MySQL8Dialect {

    public MyMysqlDialect() {
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
