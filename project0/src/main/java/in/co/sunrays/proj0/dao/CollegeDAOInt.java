package in.co.sunrays.proj0.dao;

import java.util.List;

import in.co.sunrays.proj0.dto.CollegeDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface CollegeDAOInt {

	public long add(CollegeDTO dto) throws DuplicateRecordException;

	public void update(CollegeDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public CollegeDTO findByPk(long pk) throws DataBaseException;

	public CollegeDTO findByName(String name) throws DataBaseException;

	public List<CollegeDTO> search(CollegeDTO dto) throws DataBaseException;

	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) throws DataBaseException;

}
