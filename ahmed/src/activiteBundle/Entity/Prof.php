<?php

namespace activiteBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Prof
 *
 * @ORM\Table(name="prof")
 * @ORM\Entity(repositoryClass="activiteBundle\Repository\ProfRepository")
 */
class Prof
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_prof", type="string", length=255)
     */
    private $nomProf;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nomProf
     *
     * @param string $nomProf
     *
     * @return Prof
     */
    public function setNomProf($nomProf)
    {
        $this->nomProf = $nomProf;

        return $this;
    }

    /**
     * Get nomProf
     *
     * @return string
     */
    public function getNomProf()
    {
        return $this->nomProf;
    }
}

