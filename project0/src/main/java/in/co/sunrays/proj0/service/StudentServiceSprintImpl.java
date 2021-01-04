package in.co.sunrays.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.StudentDAOInt;
import in.co.sunrays.proj0.dto.CollegeDTO;
import in.co.sunrays.proj0.dto.StudentDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Service("studentService")
public class StudentServiceSprintImpl implements StudentServiceInt {
	@Autowired
	private StudentDAOInt studentDao = null;

	@Autowired
	private CollegeServiceInt collegeservice = null;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(StudentDTO dto) throws DuplicateRecordException {
		long pk = 0;

		try {
			StudentDTO existDTO = studentDao.findByLogin(dto.getEmailId());
			if (existDTO != null) {
				throw new DuplicateRecordException("StudentEmail  Already Exist");
			}
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		CollegeDTO cdto = null;
		try {
			cdto = (CollegeDTO) collegeservice.findByPK(dto.getCollegeId());
			dto.setCollegeName(cdto.getName());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pk = studentDao.add(dto);
		return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(StudentDTO dto) throws DuplicateRecordException {
		studentDao.update(dto);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {

		studentDao.delete(id);
	}

	@Transactional(readOnly = true)
	public StudentDTO findByLogin(String login) throws DataBaseException {
		StudentDTO dto = null;
		dto = studentDao.findByLogin(login);
		return dto;
	}

	@Transactional(readOnly = true)
	public StudentDTO findByPK(long pk) throws DataBaseException {
		StudentDTO dto = null;
		dto = studentDao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(StudentDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		list = studentDao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(readOnly = true)
	public List search(StudentDTO dto) throws DataBaseException {
		List list = null;
		list = studentDao.search(dto);
		return list;
	}

}
