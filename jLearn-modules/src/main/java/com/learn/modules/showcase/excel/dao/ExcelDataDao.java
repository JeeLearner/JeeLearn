package com.learn.modules.showcase.excel.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.learn.common.jdbc.jpa.dao.BaseRepository;
import com.learn.modules.showcase.excel.entity.ExcelData;

public interface ExcelDataDao extends BaseRepository<ExcelData, Long> {

	public void truncate();
	
	@Modifying
    @Query(value = "insert into showcase_excel_data (id, content) values(?1,?2)", nativeQuery = true)
	public void save(Long id, String content);
}
