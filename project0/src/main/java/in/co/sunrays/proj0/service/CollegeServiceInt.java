package in.co.sunrays.proj0.service;

import java.util.List;

import in.co.sunrays.proj0.dto.CollegeDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface CollegeServiceInt {

	public long add(CollegeDTO dto) throws DuplicateRecordException;

	public void update(CollegeDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public CollegeDTO findByName(String Name) throws DataBaseException;

	public CollegeDTO findByPK(long pk) throws DataBaseException;

	public List search(CollegeDTO dto, int pageNo, int pageSize) throws DataBaseException;

	public List search(CollegeDTO dto) throws DataBaseException;

}
