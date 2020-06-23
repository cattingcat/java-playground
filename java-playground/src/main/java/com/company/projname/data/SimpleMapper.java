package com.company.projname.data;

import org.apache.ibatis.annotations.Select;

public interface SimpleMapper {

    @Select("""
            SELECT COUNT(*) FROM test
            """)
    int count();
}
