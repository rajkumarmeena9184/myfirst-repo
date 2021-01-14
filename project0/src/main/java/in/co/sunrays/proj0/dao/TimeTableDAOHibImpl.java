package in.co.sunrays.proj0.dao;

import java.util.Date;
import java.util.List;
import javax.mail.search.SubjectTerm;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import in.co.sunrays.proj0.dto.CourseDTO;
import in.co.sunrays.proj0.dto.SubjectDTO;
import in.co.sunrays.proj0.dto.TimeTableDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

/**
 * TimeTable Data Access Object provides Database CRUD operations. It is
 * implemented by plain Hibernate 3 API with Spring ORM.
 * 
 * All methods propagate unchecked DataAccessException. It is a generic
 * exception handling provided by Spring-DAO.
 * 
 * If DataAccessException is propagated from method then declarative transaction
 * is rolled back by Spring AOP.
 * 
 * This is plain Hibernate 3 API implementation of DAO
 * 
 * @author Iterator Copyright (c) Iterator
 */
@Repository("timeTableDao")
public class TimeTableDAOHibImpl implements TimeTableDAOInt {
	Logger log = Logger.getLogger(TimeTableDAOHibImpl.class);
	@Autowired
	private SessionFactory sessionFactory = null;
	@Autowired
	private CourseDAOInt courseDao = null;
	@Autowired
	private SubjectDAOInt subjectDao = null;

	public long add(TimeTableDTO dto) throws DuplicateRecordException {
		log.debug("TimeTableDAOHibImpl method Add Start");
		long pk = 0;
		CourseDTO courseDTO = null;
		SubjectDTO subjectDTO = null;
		try {
			courseDTO = courseDao.findByPk(dto.getCourseId());
			subjectDTO = subjectDao.findByPk(dto.getSubjectId());
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setCourseName(courseDTO.getName());
		dto.setSubjectName(subjectDTO.getSubjectName());
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("TimeTableDAOHibImpl method Add End");
		return pk;
	}

	public void update(TimeTableDTO dto) throws DuplicateRecordException {
		log.debug("TimeTableDAOHibImpl method Update Start");
		CourseDTO courseDto = courseDao.findByPk(dto.getCourseId());
		SubjectDTO subjectDto = null;
		try {
			subjectDto = subjectDao.findByPk(dto.getSubjectId());

		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setCourseName(courseDto.getName());
		dto.setSubjectName(subjectDto.getSubjectName());
		sessionFactory.getCurrentSession().merge(dto);
		log.debug("TimeTableDAOHibImpl method Update End");
	}

	public void delete(long id) throws DataBaseException {
		log.debug("TimeTableDAOHibImpl method Delete Start");
		TimeTableDTO dto = findByPk(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("TimeTableDAOHibImpl method Delete End");
	}

	public TimeTableDTO findByPk(long pk) throws DataBaseException {
		log.debug("TimeTableDAOHibImpl method FindByPk Start");
		TimeTableDTO dto = null;
		dto = (TimeTableDTO) sessionFactory.getCurrentSession().get(TimeTableDTO.class, pk);
		log.debug("TimeTableDAOHibImpl method FindByPk End");
		return dto;
	}

	public TimeTableDTO findByTimeTableDublicacy(long courseId, String semester, Date date) throws DataBaseException {
		log.debug("TimeTableDAOHibImpl method findByTimeTableDublicacy Start");
		TimeTableDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimeTableDTO.class);
		criteria.add(Restrictions.eq("courseId", courseId));
		criteria.add(Restrictions.eq("semester", semester));
		criteria.add(Restrictions.eq("date", date));
		List list = criteria.list();
		if (list.size() == 1) {
			dto = (TimeTableDTO) list.get(0);
		}
		log.debug("TimeTableDAOHibImpl method findByTimeTableDublicacy End");
		return dto;
	}

	public TimeTableDTO findByTimeTableDublicacy(long courseId, String semester, long subjectId)
			throws DataBaseException {
		log.debug("TimeTableDAOHibImpl method findByTimeTableDublicacy Start");
		TimeTableDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimeTableDTO.class);
		criteria.add(Restrictions.eq("courseId", courseId));
		criteria.add(Restrictions.eq("semester", semester));
		criteria.add(Restrictions.eq("subjectId", subjectId));
		List list = criteria.list();
		if (list.size() == 1) {
			dto = (TimeTableDTO) list.get(0);
		}
		log.debug("TimeTableDAOHibImpl method findByTimeTableDublicacy End");
		return dto;
	}

	public TimeTableDTO findByTimeTableDublicacy(long courseId, long subjectId, Date date) throws DataBaseException {
		log.debug("TimeTableDAOHibImpl method findByTimeTableDublicacy Start");
		TimeTableDTO dto = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimeTableDTO.class);
		criteria.add(Restrictions.eq("courseId", courseId));
		criteria.add(Restrictions.eq("subjectId", subjectId));
		criteria.add(Restrictions.eq("date", date));
		List list = criteria.list();
		if (list.size() == 1) {
			dto = (TimeTableDTO) list.get(0);
		}
		log.debug("TimeTableDAOHibImpl method findByTimeTableDublicacy End");
		return dto;
	}

	public TimeTableDTO findByLogin(String login) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TimeTableDTO> search(TimeTableDTO dto) throws DataBaseException {
		log.debug("TimeTableDAOHibImpl method search start");
		List list = search(dto, 0, 0);
		log.debug("TimeTableDAOHibImpl method search End");
		return list;
	}

	public List<TimeTableDTO> search(TimeTableDTO dto, int pageNo, int pageSize) throws DataBaseException {
		log.debug("TimeTableDAOHibImpl method search with Pagination start");
		List list = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimeTableDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCourseId() > 0) {
				criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
			}
			if (dto.getSubjectId() > 0) {
				criteria.add(Restrictions.eq("subjectId", dto.getSubjectId()));
			}
			if (dto.getDate() != null) {
				criteria.add(Restrictions.eq("date", dto.getDate()));
			}
			if (dto.getCourseName() != null) {
				criteria.add(Restrictions.like("courseName", dto.getCourseName()));
			}
			if (dto.getSubjectName() != null) {
				criteria.add(Restrictions.like("subjectName", dto.getSubjectName()));
			}
			if (dto.getExameTime() != null) {
				criteria.add(Restrictions.like("exameTime", dto.getExameTime()));
			}
		}
		// if pageSize greater then zero then pagination
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);

		}
		list = criteria.list();
		log.debug("TimeTableDAOHibImpl method search with Pagination End");
		return list;
	}

}
