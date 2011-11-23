package org.sakaiproject.delegatedaccess.logic;

import java.util.Date;
import java.util.List;

import javax.swing.tree.TreeModel;

import org.sakaiproject.delegatedaccess.model.HierarchyNodeSerialized;
import org.sakaiproject.delegatedaccess.model.NodeModel;
import org.sakaiproject.delegatedaccess.model.SearchResult;
import org.sakaiproject.delegatedaccess.model.ToolSerialized;



/**
 * Delegated Access's logic interface
 * 
 * @author Bryan Holladay (holladay@longsight.com)
 *
 */
public interface ProjectLogic {

	/**
	 * updates the user's Session to include the site and their access to the delegatedaccess.accessmap Session attribute.  This controls the user's 
	 * permissions for that site.  If the nodeId doesn't have an access role specified, it will grant the inherited access role.
	 * 
	 * @param nodeModel
	 */
	public void grantAccessToSite(NodeModel nodeModel);

	/**
	 * Returns a list of SearchResults for the user search
	 * 
	 * @param search
	 * @param first
	 * @param last
	 * @return
	 */
	public List<SearchResult> searchUsers(String search, int first, int last);

	/**
	 * Removes old user permissions and replaces it with the passed in information.
	 * 
	 * @param nodeModel
	 * @param userId
	 */
	public void updateNodePermissionsForUser(NodeModel nodeModel, String userId);

	/**
	 * updates the user's Session adding all of the user's site and role access to the delegatedaccess.accessmap Session attribute.  This controls the user's 
	 * permissions for that site.  If the nodeId doesn't have an access role specified, it will grant the inherited access role.
	 * 
	 */
	public void initializeDelegatedAccessSession();

	/**
	 * Searches user access sites by siteId and siteTitle
	 * 
	 * @param search
	 * @param treeModel
	 * @return
	 */
	public List<NodeModel> searchUserSites(String search, TreeModel treeModel);

	/**
	 * returns the tree model of a user's delegated access.  Each node in the tree has the NodeModel object
	 * completely initialized.
	 * 
	 * @param userId
	 * @param addDirectChildren
	 * @param cascade
	 * @return
	 */

	public TreeModel createTreeModelForUser(String userId, boolean addDirectChildren, boolean cascade);

	/**
	 * returns the tree model that looks up the shopping period information for the sites the user has access to
	 * 
	 * @param userId
	 * @return
	 */
	public TreeModel createTreeModelForShoppingPeriod(String userId);
	
	/**
	 * Adds children node to a node that hasn't had it's children populated.  This is used to increase the efficiency
	 * of the tree so you can create the structure on the fly with ajax
	 * 
	 * @param node
	 * @param userId
	 * @param blankRestrictedTools
	 * @return
	 */
	public boolean addChildrenNodes(Object node, String userId, List<ToolSerialized> blankRestrictedTools);
		
	/**
	 * returns a blank (unselected) list of all the tool options for restricting tools
	 * @return
	 */
	public List<ToolSerialized> getEntireToolsList();
	
	
	public NodeModel getNodeModel(String nodeId, String userId);
	
	public HierarchyNodeSerialized getNode(String id);
	
	/**
	 * Store a permission on a hierarchy node
	 * 
	 * @param userId
	 *            the id of the user being granted a permission
	 * @param nodeId
	 *            the id of the hierarchy node
	 * @param perm
	 *            the permission name
	 * @param cascade
	 *            cascade down to child nodes
	 */
	public void assignUserNodePerm(String userId, String nodeId, String perm,
			boolean cascade);
	
	
	public Date getShoppingPeriodProccessedDate(String userId, String nodeId);
	
	public TreeModel getEntireTreeForUser(String userId);
}
