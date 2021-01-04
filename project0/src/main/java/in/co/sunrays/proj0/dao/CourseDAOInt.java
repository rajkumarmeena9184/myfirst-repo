package in.co.sunrays.proj0.dao;

import java.util.List;

import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface CourseDAOInt {

	public long add(CourseDTO dto);

	public void update(CourseDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public CourseDTO findByPk(long pk);

	public CourseDTO findByName(String name);

	public List<CourseDTO> search(CourseDTO dto) throws DataBaseException;

	public List<CourseDTO> search(CourseDTO dto, int pageNo, int pageSize) throws DataBaseException;

}
