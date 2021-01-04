package in.co.sunrays.proj0.dao;

import java.util.Date;
import java.util.List;

import in.co.sunrays.proj0.dto.TimeTableDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface TimeTableDAOInt {

	public long add(TimeTableDTO dto) throws DuplicateRecordException;

	public void update(TimeTableDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public TimeTableDTO findByPk(long pk) throws DataBaseException;

	public TimeTableDTO findByTimeTableDublicacy(long courseId, String semester, Date date) throws DataBaseException;

	public TimeTableDTO findByTimeTableDublicacy(long courseId, String semester, long subjectId)
			throws DataBaseException;

	public TimeTableDTO findByTimeTableDublicacy(long courseId, long subjectId, Date date) throws DataBaseException;

	public TimeTableDTO findByLogin(String login) throws DataBaseException;

	public List<TimeTableDTO> search(TimeTableDTO dto) throws DataBaseException;

	public List<TimeTableDTO> search(TimeTableDTO dto, int pageNo, int pageSize) throws DataBaseException;
}
