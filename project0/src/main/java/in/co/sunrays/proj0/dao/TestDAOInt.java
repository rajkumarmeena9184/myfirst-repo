package in.co.sunrays.proj0.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import in.co.sunrays.proj0.dto.TestDTO;

import in.co.sunrays.proj0.exception.DuplicateRecodException;

public interface TestDAOInt {
	public long add(TestDTO dto) throws DuplicateRecodException;

	public void update(TestDTO dto) throws DuplicateRecodException;

	public void delete(long id);

	public TestDTO findByPK(long pk) throws DataAccessException;

	public List search(TestDTO dto, int pageNo, int pageSize) throws DataAccessException;

	public List search(TestDTO dto) throws DataAccessException;
}
