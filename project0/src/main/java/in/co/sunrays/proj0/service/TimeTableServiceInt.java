package in.co.sunrays.proj0.service;

import java.util.List;

import in.co.sunrays.proj0.dto.TimeTableDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface TimeTableServiceInt {

	public long add(TimeTableDTO dto) throws DuplicateRecordException;

	public void update(TimeTableDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public TimeTableDTO findByLogin(String login) throws DataBaseException;

	public TimeTableDTO findByPK(long pk) throws DataBaseException;

	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws DataBaseException;

	public List search(TimeTableDTO dto) throws DataBaseException;

}
