package in.co.sunrays.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.FacultyDAOInt;
import in.co.sunrays.proj0.dto.FacultyDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Service("facultyService")
public class FacultyServiceSpringImpl implements FacultyServiceInt {
	@Autowired
	private FacultyDAOInt facultyDao = null;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(FacultyDTO dto) throws DuplicateRecordException {
		try {
			FacultyDTO dtoExist = facultyDao.findByLogin(dto.getEmailID());
			if (dtoExist != null) {
				throw new DuplicateRecordException("Faculty Already Exist");
			}
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		long pk = facultyDao.add(dto);
		return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(FacultyDTO dto) throws DuplicateRecordException {
		facultyDao.update(dto);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		facultyDao.delete(id);

	}

	@Transactional(readOnly = true)
	public FacultyDTO findByLogin(String login) throws DataBaseException {
		FacultyDTO dto = null;
		dto = facultyDao.findByLogin(login);
		return dto;
	}

	@Transactional(readOnly = true)
	public FacultyDTO findByPK(long pk) throws DataBaseException {
		FacultyDTO dto = null;
		dto = facultyDao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(FacultyDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		list = facultyDao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(readOnly = true)
	public List search(FacultyDTO dto) throws DataBaseException {
		List list = null;
		list = facultyDao.search(dto);
		return list;
	}

}
