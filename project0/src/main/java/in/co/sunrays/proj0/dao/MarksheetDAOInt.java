package in.co.sunrays.proj0.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import in.co.sunrays.proj0.dto.MarksheetDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface MarksheetDAOInt {

	public long add(MarksheetDTO dto) throws DuplicateRecordException;

	public void update(MarksheetDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public MarksheetDTO findByPk(long pk) throws DataBaseException;

	public MarksheetDTO findByLogin(String login) throws DataBaseException;

	public MarksheetDTO findByRollNumber(String rollNo) throws DataBaseException;

	public List<MarksheetDTO> search(MarksheetDTO dto) throws DataBaseException;

	public List<MarksheetDTO> search(MarksheetDTO dto, int pageNo, int pageSize) throws DataBaseException;
	
	public List meritList(int pageNo, int pageSize) throws DataAccessException;

}
