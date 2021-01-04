package in.co.sunrays.proj0.dao;

import java.util.List;

import in.co.sunrays.proj0.dto.StudentDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface StudentDAOInt {

	public long add(StudentDTO dto) throws DuplicateRecordException;

	public void update(StudentDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public StudentDTO findByPk(long pk) throws DataBaseException;

	public StudentDTO findByLogin(String login) throws DataBaseException;

	public List<StudentDTO> search(StudentDTO dto) throws DataBaseException;

	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) throws DataBaseException;
}
