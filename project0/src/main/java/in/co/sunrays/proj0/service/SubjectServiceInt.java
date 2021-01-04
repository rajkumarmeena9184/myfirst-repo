package in.co.sunrays.proj0.service;

import java.util.List;

import in.co.sunrays.proj0.dto.SubjectDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface SubjectServiceInt {

	public long add(SubjectDTO dto) throws DuplicateRecordException;

	public void update(SubjectDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public SubjectDTO findByLogin(String login) throws DataBaseException;

	public SubjectDTO findByName(String name) throws DataBaseException;

	public SubjectDTO findByPK(long pk) throws DataBaseException;

	public List search(SubjectDTO dto, int pageNo, int pageSize) throws DataBaseException;

	public List search(SubjectDTO dto) throws DataBaseException;

}
