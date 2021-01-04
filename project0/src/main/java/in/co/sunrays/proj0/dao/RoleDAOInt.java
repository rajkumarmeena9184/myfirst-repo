package in.co.sunrays.proj0.dao;

import java.util.List;

import in.co.sunrays.proj0.dto.RoleDTO;
import in.co.sunrays.proj0.exception.DataBaseException;
import in.co.sunrays.proj0.exception.DuplicateRecordException;

public interface RoleDAOInt {

	public long add(RoleDTO dto) throws DuplicateRecordException;

	public void update(RoleDTO dto) throws DuplicateRecordException;

	public void delete(long id) throws DataBaseException;

	public RoleDTO findByPk(long pk) throws DataBaseException;

	public RoleDTO findByName(String name) throws DataBaseException;

	public RoleDTO findByLogin(String login) throws DataBaseException;

	public List<RoleDTO> search(RoleDTO dto) throws DataBaseException;
	
	public List<RoleDTO> list() throws DataBaseException;

	public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) throws DataBaseException;
}
