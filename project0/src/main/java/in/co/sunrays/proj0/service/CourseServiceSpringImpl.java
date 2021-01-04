package in.co.sunrays.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.CourseDAOInt;
import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Service("courseService")
public class CourseServiceSpringImpl implements CourseServiceInt {
	@Autowired
	private CourseDAOInt courseDao = null;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(CourseDTO dto) throws DuplicateRecordException {
		long pk = 0;

		CourseDTO dtoExist = null;
		dtoExist = courseDao.findByName(dto.getName());
		if (dtoExist != null) {
			throw new DuplicateRecordException("College Already Exist");
		}

		pk = courseDao.add(dto);
		return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(CourseDTO dto) throws DuplicateRecordException {
		courseDao.update(dto);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		courseDao.delete(id);
	}

	@Transactional(readOnly = true)
	public CourseDTO findByName(String name) throws DataBaseException {
		CourseDTO dto = null;
		dto = courseDao.findByName(name);
		return dto;
	}

	@Transactional(readOnly = true)
	public CourseDTO findByPK(long pk) throws DataBaseException {
		CourseDTO dto = null;
		dto = courseDao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(CourseDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = courseDao.search(dto, pageNo, pageSize);
		return null;
	}

	@Transactional(readOnly = true)
	public List search(CourseDTO dto) throws DataBaseException {
		List list = null;
		list = courseDao.search(dto);
		return list;
	}

}
