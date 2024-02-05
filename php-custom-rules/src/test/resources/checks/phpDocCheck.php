<?php

namespace Verebo\Models;

use AdminFox\Entities\E_Company;
use AdminFox\Entities\E_Site;
use AdminFox\Models\M_Company;
use Exception;

class M_Franchise extends M_Company
{
	public function _prepareSQL(array $filters = []): string      //NonCompliant
	{
	}

	/**
	 * Function responsible for getting the list of franchises
	 * @param array $data
	 * @return array
	 */

		public function addCompany(array $data): array        //OK
	{

	}

	/**
	 * Function responsible for updating franchises
	 * @param array $data
	 * @return array
	 */
	private function updateCompany(array $data): array       //OK
	{

	}
		/**
  	 * Function responsible for updating franchises
  	 * @param array $data
  	 * @return array
  	 */
  	function testetstest(array $data): array       //OK
  	{

  	}
	/////test
	private function coucou(array $data): array       //NonCompliant
  {

  }

  public function test2() //NonCompliant
  {
  }

  /**
  	 * Function responsible for updating franchises
  	 * @param array $data
  	 * @return array
  	 */

  	 ///// test test tse 454
  	public function test(array $data): array       //OK
  	{

  	}
}

