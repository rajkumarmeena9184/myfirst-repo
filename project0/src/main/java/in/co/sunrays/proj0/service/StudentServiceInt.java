package in.co.sunrays.proj0.service;

import java.util.List;

import in.co.sunrays.proj0.dto.StudentDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface StudentServiceInt {

	public long add(StudentDTO dto) throws DuplicateRecordException;

	public void update(StudentDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public StudentDTO findByLogin(String login) throws DataBaseException;

	public StudentDTO findByPK(long pk) throws DataBaseException;

	public List search(StudentDTO dto, int pageNo, int pageSize) throws DataBaseException;

	public List search(StudentDTO dto) throws DataBaseException;

}
