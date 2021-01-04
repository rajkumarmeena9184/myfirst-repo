package in.co.sunrays.proj0.service;

import java.util.List;

import in.co.sunrays.proj0.dto.FacultyDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface FacultyServiceInt {


	public long add(FacultyDTO dto) throws DuplicateRecordException;

	public void update(FacultyDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public FacultyDTO findByLogin(String login) throws DataBaseException;

	public FacultyDTO findByPK(long pk) throws DataBaseException;

	public List search(FacultyDTO dto, int pageNo, int pageSize) throws DataBaseException;

	public List search(FacultyDTO dto) throws DataBaseException;


}
