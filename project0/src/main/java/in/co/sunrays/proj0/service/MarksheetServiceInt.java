package in.co.sunrays.proj0.service;

import java.util.List;

import in.co.sunrays.proj0.dto.MarksheetDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface MarksheetServiceInt {

	public long add(MarksheetDTO dto) throws DuplicateRecordException;

	public void update(MarksheetDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public MarksheetDTO findByLogin(String login) throws DataBaseException;

	public MarksheetDTO findByPK(long pk) throws DataBaseException;

	public MarksheetDTO findByRollNumber(String rollNo) throws DataBaseException;

	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws DataBaseException;

	public List search(MarksheetDTO dto) throws DataBaseException;

}
