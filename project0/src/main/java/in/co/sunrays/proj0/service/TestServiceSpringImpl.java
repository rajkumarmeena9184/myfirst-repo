package in.co.sunrays.proj0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.sunrays.proj0.dao.TestDAOInt;
import in.co.sunrays.proj0.dto.TestDTO;
import in.co.sunrays.proj0.exception.DuplicateRecodException;

@Service(value = "testservice")
public class TestServiceSpringImpl implements TestServiceInt {
	@Autowired
	private TestDAOInt testDao;

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public long add(TestDTO dto) throws DuplicateRecodException {

		long pk = 0;
		pk = testDao.add(dto);

		return pk;
	}

	public void update(TestDTO dto) throws DuplicateRecodException {
		// TODO Auto-generated method stub

	}

	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	public TestDTO findByPK(long pk) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public List search(TestDTO dto, int pageNo, int pageSize) throws DataAccessException {
		// TODO Auto-generated method stub
		/* List list=testDao.search(dto, pageNo, pageSize); */
		return testDao.search(dto, pageNo, pageSize);

	}

	public List search(TestDTO dto) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
