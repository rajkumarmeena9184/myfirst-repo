package in.co.sunrays.proj0.dao;

import java.util.List;

import in.co.sunrays.proj0.dto.SubjectDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface SubjectDAOInt {

	public long add(SubjectDTO dto) throws DuplicateRecordException;

	public void update(SubjectDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public SubjectDTO findByPk(long pk) throws DataBaseException;

	public SubjectDTO findByLogin(String login) throws DataBaseException;

	public SubjectDTO findByName(String login) throws DataBaseException;

	public List<SubjectDTO> search(SubjectDTO dto) throws DataBaseException;

	public List<SubjectDTO> search(SubjectDTO dto, int pageNo, int pageSize) throws DataBaseException;
}
