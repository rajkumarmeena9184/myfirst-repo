package in.co.sunrays.proj0.service;

import java.util.List;

import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface CourseServiceInt {

	public long add(CourseDTO dto) throws DuplicateRecordException;

	public void update(CourseDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public CourseDTO findByName(String name) throws DataBaseException;

	public CourseDTO findByPK(long pk) throws DataBaseException;

	public List search(CourseDTO dto, int pageNo, int pageSize) throws DataBaseException;

	public List search(CourseDTO dto) throws DataBaseException;

}
