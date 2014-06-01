/**
 * 
 * @package com.john.neteasenews
 * @type ICheckIsNeededRefresh
 * @date 2013-12-12 下午7:00:08
 * @author JohnWatson
 * @version 1.0
 */
package com.handmark.pulltorefresh.library.extras2;

/**
 * 检查是否需要更新的回调接口
 */
public interface ICheckIsNeededRefresh {
	/**
	 * 检查是否需要自动更新
	 */
	public abstract void checkRefresh();
}
