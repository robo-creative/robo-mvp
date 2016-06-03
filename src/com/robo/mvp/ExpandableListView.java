/**
 * Copyright (c) 2016 Robo Creative - https://robo-creative.github.io.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.robo.mvp;

/**
 * Defines a view that displays a list of expandable items.
 * 
 * @author robo-admin
 *
 * @param <TParent>
 *            Parent item type.
 * @param <TChild>
 *            Child item type.
 */
public interface ExpandableListView<TParent, TChild> extends View<ExpandableListView.ViewModel<TParent, TChild>> {

	void setSelectedGroup(int groupPosition);

	boolean setSelectedChild(int groupPosition, int childPosition, boolean shouldExpandGroup);

	long getSelectedPosition();

	long getSelectedId();

	interface OnGroupCollapseExpandListener {

		void onGroupCollapse(int groupPosition);

		void onGroupExpand(int groupPosition);
	}

	interface OnChildClickListener {
		boolean onChildClick(int parentPosition, int childPosition, long id);
	}

	public class ViewModel<TParent, TChild> {

		private TParent[] parents;
		private TChild[][] children;

		public ViewModel(TParent[] parents, TChild[][] children) {
			this.parents = parents;
			this.children = children;
		}

		public TParent[] getParents() {
			return parents;
		}

		public TChild[][] getChildren() {
			return children;
		}
	}
}
