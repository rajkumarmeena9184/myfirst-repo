package in.co.sunrays.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.MarksheetDAOInt;
import in.co.sunrays.proj0.dto.MarksheetDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Service("marksheetService")
public class MarksheetServiceSpringImpl implements MarksheetServiceInt {
	@Autowired
	private MarksheetDAOInt marksheetDao = null;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(MarksheetDTO dto) throws DuplicateRecordException {
		long pk = 0;

		try {
			MarksheetDTO dtoExist = marksheetDao.findByRollNumber(dto.getRollnumber());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Roll_no already exist");
			}
		} catch (DataBaseException e) {
			e.printStackTrace();
		}

		pk = (Long) marksheetDao.add(dto);

		return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(MarksheetDTO dto) throws DuplicateRecordException {

		marksheetDao.update(dto);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		marksheetDao.delete(id);
	}

	@Transactional(readOnly = true)
	public MarksheetDTO findByRollNumber(String rollNo) throws DataBaseException {
		MarksheetDTO dto = marksheetDao.findByRollNumber(rollNo);
		return dto;
	}

	@Transactional(readOnly = true)
	public MarksheetDTO findByLogin(String login) throws DataBaseException {
		return null;
	}

	@Transactional(readOnly = true)
	public MarksheetDTO findByPK(long pk) throws DataBaseException {
		MarksheetDTO dto = marksheetDao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws DataBaseException {

		List list = marksheetDao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto) throws DataBaseException {
		List list = marksheetDao.search(dto);
		return list;
	}

}
