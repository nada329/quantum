<?php

namespace activiteBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Cours
 *
 * @ORM\Table(name="cours")
 * @ORM\Entity(repositoryClass="activiteBundle\Repository\CoursRepository")
 */
class Cours
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
     * @ORM\Column(name="libelle", type="string", length=255)
     */
    private $libelle;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;



    /**
     * @Assert\File(maxSize="500k")
     */
    public $file;



    /**
     * @ORM\ManyToOne(targetEntity="Prof")
     * @ORM\JoinColumn(name="prof_id",referencedColumnName="id")
     */
    private $prof;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255)
     */
    private $image;



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
     * Set libelle
     *
     * @param string $libelle
     *
     * @return Cours
     */
    public function setLibelle($libelle)
    {
        $this->libelle = $libelle;

        return $this;
    }

    /**
     * Get libelle
     *
     * @return string
     */
    public function getLibelle()
    {
        return $this->libelle;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Cours
     */
    public function setnom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getnom()
    {
        return $this->nom;
    }

    /**
     * Set nom
     *
     * @param string $image
     *
     * @return Cours
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set prof
     *
     * @param \activiteBundle\Entity\Prof $prof
     *
     * @return Cours
     */
    public function setProf(\activiteBundle\Entity\Prof $prof = null)
    {
        $this->prof = $prof;

        return $this;
    }

    /**
     * Get prof
     *
     * @return \activiteBundle\Entity\Prof
     */
    public function getProf()
    {
        return $this->prof;
    }

    public function getWebPath(){
        return null===$this->image ? null : $this->getUploadDir.'/'.$this->image;
    }
    protected function getUploadRootDir(){
        return __DIR__.'/../../../web/uploads/images/cours/'.$this->getUploadDir();
    }
    protected function getUploadDir(){
        return 'images';
    }
    public function uploadProfilePicture(){
        $this->file->move($this->getUploadRootDir(), $this->file->getClientOriginalName());
        $this->image=$this->file->getClientOriginalName();
        $this->file=null;
    }
}
