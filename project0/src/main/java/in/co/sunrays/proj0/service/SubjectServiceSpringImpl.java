package in.co.sunrays.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.SubjectDAOInt;
import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.dto.SubjectDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Service("subjectService")
public class SubjectServiceSpringImpl implements SubjectServiceInt {
	@Autowired
	private SubjectDAOInt subjectDao = null;
	@Autowired
	private CourseServiceInt courseService = null;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(SubjectDTO dto) throws DuplicateRecordException {
		long pk = 0;

		try {
			SubjectDTO existDTO = subjectDao.findByName(dto.getSubjectName());
			if (existDTO != null) {
				throw new DuplicateRecordException("Subject Alredy Exist");
			}
			CourseDTO cdto = null;
			try {
				cdto = (CourseDTO) courseService.findByPK(dto.getCourseId());
			} catch (DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dto.setCourseName(cdto.getName());
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		pk = subjectDao.add(dto);
		return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(SubjectDTO dto) throws DuplicateRecordException {
		subjectDao.update(dto);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		subjectDao.delete(id);
	}

	@Transactional(readOnly = true)
	public SubjectDTO findByName(String name) throws DataBaseException {
		SubjectDTO dto = subjectDao.findByName(name);
		return dto;
	}

	public SubjectDTO findByLogin(String login) throws DataBaseException {
		return null;
	}

	@Transactional(readOnly = true)
	public SubjectDTO findByPK(long pk) throws DataBaseException {
		SubjectDTO dto = subjectDao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(SubjectDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		list = subjectDao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(readOnly = true)
	public List search(SubjectDTO dto) throws DataBaseException {
		List list = null;
		list = subjectDao.search(dto);
		return list;
	}

}
