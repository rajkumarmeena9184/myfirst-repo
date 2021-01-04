package in.co.sunrays.proj0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import in.co.sunrays.proj0.dto.TestDTO;
import in.co.sunrays.proj0.exception.DuplicateRecodException;

@Repository(value = "testDao")
public class TestDAOHibImpl implements TestDAOInt {
	@Autowired
	private SessionFactory sessionFactory = null;

	public long add(TestDTO dto) throws DuplicateRecodException {
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);

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

	public List search(TestDTO dto, int pageNo, int pageSize) throws DataAccessException {
		// TODO Auto-generated method stub
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TestDTO.class);
		list = criteria.list();
		return list;
	}

	public List search(TestDTO dto) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
