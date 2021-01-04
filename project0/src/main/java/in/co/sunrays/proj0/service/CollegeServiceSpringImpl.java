package in.co.sunrays.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.CollegeDAOInt;
import in.co.sunrays.proj0.dto.CollegeDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

@Service(value = "collegeService")
public class CollegeServiceSpringImpl implements CollegeServiceInt {
	@Autowired
	private CollegeDAOInt collegeDao = null;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public long add(CollegeDTO dto) throws DuplicateRecordException {
		CollegeDTO cdto = null;

		try {
			cdto = findByName(dto.getName());
			if (cdto != null) {
				throw new DuplicateRecordException("College already Exist");
			}
		} catch (DataBaseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		long pk = collegeDao.add(dto);
		return pk;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(CollegeDTO dto) throws DuplicateRecordException {
		collegeDao.update(dto);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(long id) throws DataBaseException {
		collegeDao.delete(id);
	}

	public CollegeDTO findByName(String name) throws DataBaseException {
		CollegeDTO dto = collegeDao.findByName(name);
		return dto;
	}

	@Transactional(readOnly = true)
	public CollegeDTO findByPK(long pk) throws DataBaseException {
		CollegeDTO dto = null;
		dto = collegeDao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(CollegeDTO dto, int pageNo, int pageSize) throws DataBaseException {
		List list = null;
		list = collegeDao.search(dto, pageNo, pageSize);
		return list;
	}

	@Transactional(readOnly = true)
	public List search(CollegeDTO dto) throws DataBaseException {
		List list = null;
		list = collegeDao.search(dto);
		return list;
	}

}
