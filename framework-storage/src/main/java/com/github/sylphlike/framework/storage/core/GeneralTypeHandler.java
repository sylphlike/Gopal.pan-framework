package com.github.sylphlike.framework.storage.core;

import com.github.sylphlike.framework.norm.StorageInterface;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 通用枚举映射处理器
 * <p>  time 19:28 2020/07/05  星期一 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public class GeneralTypeHandler <E extends StorageInterface<?,?>> extends BaseTypeHandler<E> {

    private final E[] enums;


    public GeneralTypeHandler(Class<E> type) {
        if (type == null) {
            throw new EnumsTypeException("Type argument cannot be null");
        }
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new EnumsTypeException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {

        if (jdbcType == null) {
            ps.setObject(i, parameter.code());
        } else {
            ps.setObject(i, parameter.code(), jdbcType.TYPE_CODE);
        }

    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {

        Object code = rs.getObject(columnName);

        if (rs.wasNull()) {
            return null;
        }

        return getEnmByCode(code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object code = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        }

        return getEnmByCode(code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object code = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        }

        return getEnmByCode(code);
    }

    private E getEnmByCode(Object code) {

        if (code == null) {
            throw new EnumsTypeException("the result code is null " );
        }

        if (code instanceof Integer) {
            for (E e : enums) {
                if (e.code() == code) {
                    return e;
                }
            }
            throw new EnumsTypeException("Unknown enumeration type , please check the enumeration code :  " + code);
        }


        if (code instanceof String) {
            for (E e : enums) {
                if (code.equals(e.code())) {
                    return e;
                }
            }
            throw new EnumsTypeException("Unknown enumeration type , please check the enumeration code :  " + code);
        }
        throw new EnumsTypeException("Unknown enumeration type , please check the enumeration code :  " + code);
    }
}
