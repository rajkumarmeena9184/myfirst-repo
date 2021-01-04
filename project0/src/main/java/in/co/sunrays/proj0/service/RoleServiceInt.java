package in.co.sunrays.proj0.service;

import java.util.List;

import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface RoleServiceInt {

	public long add(RoleDTO dto) throws DuplicateRecordException;

	public void update(RoleDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public RoleDTO findByLogin(String login) throws DataBaseException;

	public RoleDTO findByName(String nsme) throws DataBaseException;

	public RoleDTO findByPK(long pk) throws DataBaseException;

	public List search(RoleDTO dto, int pageNo, int pageSize) throws DataBaseException;

	public List search(RoleDTO dto) throws DataBaseException;
	
	public List list() throws DataBaseException;

}
